package net.engineeringdigest.journalApp.appcache;


import lombok.Data;
import net.engineeringdigest.journalApp.Repository.Config;
import net.engineeringdigest.journalApp.entity.ConfigPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class AppCache {

    public enum keys{
        WEATHER_API
    }

    @Autowired
    private Config config;

    private Map<String,String> appCacheMap = new HashMap<>();

    @PostConstruct
    void init(){
        List<ConfigPojo> all = config.findAll();
        for(ConfigPojo configPojo:all){
            appCacheMap.put(configPojo.getKey(),configPojo.getValue());
        }
    }

}
