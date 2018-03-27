package com.kbn1798.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.kbn1798.core.MainRunner;

import sx.blah.discord.handle.obj.IGuild;

public class YamlGen {
    /**
     * A method designed to dump a new yaml file with a preset config at a given location
     * @param loc Where the file is to be created
     */
    public static void createNewYaml(String loc) {
		Yaml yaml = new Yaml();
    	Map<String, Object> data = new HashMap<String, Object>();
    	HashMap<String, String> poroPics = new HashMap<String, String>();
    	poroPics.put("Mitchel", "https://i.imgur.com/qGWNJI6.jpg");poroPics.put("Dorian", "https://i.imgur.com/eY7722M.jpg");
    	poroPics.put("Rupert", "https://i.imgur.com/YHEneSA.jpg");poroPics.put("Pip", "https://i.imgur.com/IRz71Sq.png");
    	poroPics.put("Mort", "https://i.imgur.com/Ir8OP15.png");poroPics.put("Pip", "https://i.imgur.com/VrENQFY.png");
    	poroPics.put("Maddie", "https://i.imgur.com/Xz1Hm06.jpg");poroPics.put("Varo", "https://i.imgur.com/GFs2KN3.jpg");
    	poroPics.put("Arco", "https://i.imgur.com/e2LeJ6Z.jpg");poroPics.put("Shakira", "https://i.imgur.com/mdM9Q08.jpg");

    	data.put("poroPics", poroPics);
    	ArrayList<String> poroIntros = new ArrayList<String>();
    	poroIntros.add("Soft lil thing for you!");poroIntros.add("Made of magic, love, and a lil bit of sugar.");
    	poroIntros.add("This one seems to like you, aww.");poroIntros.add("Poros make the best pets!");
    	poroIntros.add("Snaks! Does anyone have any snaks for the lil guy!?");poroIntros.add("Ever seen one this fluffy?");
    	poroIntros.add("Even in the coldest climates, poros never stop smiling.");poroIntros.add("You can never lose a poro, they're just real good at hide and seek.");
    	poroIntros.add("I think this one will do just fine.");
    	data.put("poroIntros", poroIntros);
		try {
			yaml.dump(data, new FileWriter(loc));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    
	/***
     * Acts as a quick call to save the config of a given instance of the
     * program.
     */
    public static void saveConfig(long id) {
    	Yaml yaml = new Yaml();
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("poroPics", MainRunner.config.get(id).getPoroPics());
    	data.put("poroIntros", MainRunner.config.get(id).getPoroIntros());
    	try {
    		yaml.dump(data, new FileWriter(MainRunner.folder.getAbsolutePath()+"\\"+id+".yml"));
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    

    public static void checkConfig(){
    	List<IGuild> guilds = MainRunner.client.getGuilds();
    	String[] folderFiles = MainRunner.folder.list();
    	
		if(MainRunner.folder.list().length>0) {
			for(IGuild g: guilds) {

    			Yaml yaml = new Yaml();

    			if(Arrays.asList(folderFiles).contains(g.getLongID()+".yml")) {//if #.yml exists in the folder list
    				try( InputStream in = Files.newInputStream(  Paths.get(MainRunner.folder.getAbsolutePath()+"\\"+g.getLongID()+".yml") ) ) {
    					MainRunner.config.put(g.getLongID(), yaml.loadAs( in, Configuration.class ));
    				}catch(IOException e){
    					e.printStackTrace();
    				}
    			}else {//if #.yml doesn't exist in folder list
    				createNewYaml(MainRunner.folder.getAbsolutePath()+"\\"+g.getLongID()+".yml");
    				try( InputStream in = Files.newInputStream( Paths.get(MainRunner.folder.getAbsolutePath()+"\\"+g.getLongID()+".yml") ) ) {
    					MainRunner.config.put(g.getLongID(), yaml.loadAs( in, Configuration.class ));
    				}catch(IOException e){
    				e.printStackTrace();
    				}
    			}
    		}
    	}else {
    		for(IGuild g: guilds) {
    			Yaml yaml = new Yaml();
    			
				YamlGen.createNewYaml(MainRunner.folder.getAbsolutePath()+"\\"+g.getLongID()+".yml");
				try( InputStream in = Files.newInputStream( Paths.get(MainRunner.folder.getAbsolutePath()+"\\"+g.getLongID()+".yml") ) ) {
					MainRunner.config.put(g.getLongID(), yaml.loadAs( in, Configuration.class ));
				}catch(IOException e){
				e.printStackTrace();
				}
    		}
    	}
    }
}
