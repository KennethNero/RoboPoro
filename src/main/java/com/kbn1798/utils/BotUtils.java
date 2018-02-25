package com.kbn1798.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

public class BotUtils {

    // Constants for use throughout the bot
    public static String BOT_PREFIX = "/";

    // Handles the creation and getting of a IDiscordClient object for a token
    public static IDiscordClient getBuiltDiscordClient(String token){

        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        return new ClientBuilder()
                .withToken(token)
                .build();

    }
    
    /**
     * Sends a file from URL with a given message.
     * 
     * @param channel Channel from which the event is being handled
     * @throws MalformedURLException Because people suck with files
     * @throws IOException And occasionally people suck with files
     */
    public static void sendFileFromURL(IChannel channel, String url, String msgText, String fileName) throws MalformedURLException, IOException {
    	InputStream input = new URL(url).openStream();
    	RequestBuffer.request(() ->{
    		try {
    			channel.sendFile(msgText, false, input, fileName);
    		}catch (DiscordException e){
    			System.err.println("File could not be sent with error: ");
    			e.printStackTrace();
    			
    		}
    	});
    }
    
    public static void embedFileFromURL(IChannel channel, String url, String msg, String title) throws MalformedURLException, IOException{
    	EmbedBuilder i = new EmbedBuilder();
    	i.withTitle(title);
    	i.withImage(url);
    	i.withDesc(msg);
    	EmbedObject e = i.build();
    	RequestBuffer.request(() ->{
    		try {
    			channel.sendMessage(e);
    		}catch (DiscordException ex){
    			System.err.println("File could not be sent with error: ");
    			ex.printStackTrace();
    			
    		}
    	});
    }
    

    // Helper functions to make certain aspects of the bot easier to use.
    public static void sendMessage(IChannel channel, String message){

        // This might look weird but it'll be explained in another page.
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
        

		/*
		// The below example is written to demonstrate sending a message if you want to catch the RLE for logging purposes
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (RateLimitException e){
                System.out.println("Do some logging");
                throw e;
            }
        });
        */

    }
}