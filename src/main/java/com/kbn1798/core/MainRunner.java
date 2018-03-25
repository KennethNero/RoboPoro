package com.kbn1798.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.kbn1798.utils.BotUtils;
import com.kbn1798.utils.Configuration;

import sx.blah.discord.api.IDiscordClient;

public class MainRunner {
	public static Configuration config;
	public static String path;
    public static void main(String[] args) throws IOException{
    	//Main malformed input check block.
        if(args.length != 2){
        	if(args.length != 1) {
        		System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
                return;
        	}else if(args.length !=2) {
        		System.out.println("Please enter the name of the configuration file as the second argument e.g java -jar thisjar.jar tokenhere confighere");
        		return;
        	}else {
        		System.out.println("Too many arguments, you've scared all the poros away.");
        	}
        }
        
        //Configuration set up and init using snakeyaml
        Yaml yaml = new Yaml();  
        path = args[1];
        try( InputStream in = Files.newInputStream( Paths.get(path) ) ) {
            config = yaml.loadAs( in, Configuration.class );
        }catch(Exception e){
        	File f = new File(args[1]);
        	f.createNewFile();
        	InputStream in = Files.newInputStream( Paths.get(path) );
        	config = yaml.loadAs( in, Configuration.class );
        }
        //System.out.println(config.toString());

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(args[0]);

        // Register a listener via the EventSubscriber annotation which allows for organization and delegation of events
        cli.getDispatcher().registerListener(new MyEvents());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();

    }
    
    
    public static void saveConfig() {
    	Yaml yaml = new Yaml();
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("poroPics", config.getPoroPics());
    	data.put("poroIntros", config.getPoroIntros());
    	try {
    		yaml.dump(data, new FileWriter(path));
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }


}