package com.alexcomeau.bot.commands.easterEggs;

import com.alexcomeau.bot.commands.CommandType;
import com.alexcomeau.utils.CommandParser;
import com.alexcomeau.utils.Debug;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Locale;
import java.util.Random;

public class EasterEgg {
    public static boolean easterEgg(Message msg, MessageReceivedEvent event, String prefix){
        final String pat = "332583326424629259";
        final String zach = "238022080753434625";
        final String logan = "425736837538250762";
        final String alex = "227478475760599041";
        final String shane = "397866822625525770";

        //randomly tell pat to shut up
        if(msg.getAuthor().getId().equals(pat)){
            //Debug.debug("pat test in server " +  msg.getGuild().getId());
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            int rand = r.nextInt(50);
            if(rand != 30){
                //Debug.debug("not telling pat to shut up in server " +  msg.getGuild().getId());

            }else {
                //Debug.debug("sending pat message in server " + msg.getGuild().getId());
                event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/501416331179196430/834978287344156722/goose.gif").queue();
            }
        }
        //randomly call zach a liberal
        if(msg.getAuthor().getId().equals(zach)){
            //Debug.debug("zach test set off in server " + msg.getGuild().getId());
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            int rand = r.nextInt(50);
            if(rand != 40){
                Debug.debug("no goose zach " +  msg.getGuild().getId());

            }else {
                //Debug.debug("sending zach message in server " + msg.getGuild().getId());
                event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/501416331179196430/832449822699028530/ed3f6c77892c7646398fc3b80bc4f78c.png").queue();
                return true;
            }
        }
        //MONKE
        if(msg.getAuthor().getId().equals(logan)){
            //Debug.debug("logan test set off in server " + msg.getGuild().getId());
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            int rand = r.nextInt(50);
            if(rand != 35){
                //Debug.debug("no logan event" +  msg.getGuild().getId());
            }else {
                //Debug.debug("sending logan message in server " + msg.getGuild().getId());
                event.getChannel().sendMessage("MMMM, Look at this monke").queue();
            }
        }

        if(msg.getAuthor().getId().equals(shane)){
            //Debug.debug("shane test set off in server " + msg.getGuild().getId());
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            int rand = r.nextInt(50);
            if(rand != 35){
                //Debug.debug("no logan event" +  msg.getGuild().getId());
            }else {
                //Debug.debug("sending logan message in server " + msg.getGuild().getId());
                event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/683536014316404812/834995881819635792/deepfried_1619144736728.png").queue();
            }
        }



        if (CommandParser.parseCommand(msg.getContentRaw().toLowerCase(Locale.ROOT)).command == CommandType.GOOSE)
        {
            if(msg.getAuthor().getId().equals(alex)){
                msg.delete().queue();
            }
            event.getChannel().sendMessage("https://tenor.com/view/no-goose-gif-18519407").queue();
            return true;
        }
        if (CommandParser.parseCommand(msg.getContentRaw().toLowerCase(Locale.ROOT)).command == CommandType.RAT)
        {
            event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/501416331179196430/832449822699028530/ed3f6c77892c7646398fc3b80bc4f78c.png").queue();
            return true;
        }
        return false;
    }
}
