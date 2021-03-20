package com.alexcomeau;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.Random;

public class Bot extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String prefix = Main.prefix;

        Message msg = event.getMessage();
        if(msg.getAuthor().isBot()) {
            return;
        }

        //test for easter eggs
        if(EasterEgg.easterEgg(msg, event, prefix)){
            //Debug.debug("breaking from easter egg");
            return;
        }
        //weather commands test
        try {
            //Debug.debug("trying weather commands");
            WeatherCommands.weatherCommands(event, msg, prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //generic ping test
        if (msg.getContentRaw().equals( prefix + "ping"))
        {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
            return;
        }








    }
}
