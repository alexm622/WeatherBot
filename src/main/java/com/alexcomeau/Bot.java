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
        String pat = "332583326424629259";
        String zach = "238022080753434625";

        Message msg = event.getMessage();
        /*
        Debug.debug("Message ID: " + msg.getId());
        Debug.debug("User ID: " + msg.getAuthor().getId());
        Debug.debug("Channel ID: " + msg.getChannel().getId());
        Debug.debug("Server ID: " +  msg.getGuild().getId());
        */
        if(msg.getAuthor().isBot()) {
            return;
        }
        //randomly tell pat to shut up
        if(msg.getAuthor().getId().equals(pat)){
            Debug.debug("pat test in server " +  msg.getGuild().getId());
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            int rand = r.nextInt(100);
             if(rand != 69){
                 Debug.debug("not telling pat to shut up in server " +  msg.getGuild().getId());
                 return;
             }
             Debug.debug("sending pat message in server " +  msg.getGuild().getId());
             event.getChannel().sendMessage("Shut up pat, you're dead").queue();
             return;
        }
        //randomly call zach a liberal
        if(msg.getAuthor().getId().equals(zach)){
            Debug.debug("zach test set off in server " + msg.getGuild().getId());
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            int rand = r.nextInt(100);
            if(rand != 82){
                Debug.debug("not calling zach liberal in server " +  msg.getGuild().getId());
                return;
            }
            Debug.debug("sending zach message in server " +  msg.getGuild().getId());
            event.getChannel().sendMessage("Shut up Liberal").queue();
            return;
        }
        if (msg.getContentRaw().equals("&ping"))
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
