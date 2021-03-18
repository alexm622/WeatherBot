package com.alexcomeau;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class Bot extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String prefix = Main.prefix;

        Message msg = event.getMessage();
        //test for easter eggs
        EasterEgg.easterEgg(msg, event, prefix);
        //weather commands test
        WeatherCommands.weatherCommands(event, msg, prefix);


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



        if(msg.getAuthor().isBot()) {
            return;
        }




    }
}
