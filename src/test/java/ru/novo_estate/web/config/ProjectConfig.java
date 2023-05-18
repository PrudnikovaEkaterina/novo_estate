package ru.novo_estate.web.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)//аннотация, чтобы указать Owner, что нужно объединить файлы properties
@Config.Sources({ // сообщаем Owner загрузить файлы с раширением .properties, которые находятся в папке resources
        "system:properties",
        "classpath:${env}.properties",
        "file:~/${env}.properties",
        "file:./${env}.properties"
})

public interface ProjectConfig extends Config { //интерфейс (это требуется для Owner) и расширяем ее для использования класса Config(этот класс делает большую часть магии Owner).
    //здесь перечислим все properties из файлов с расширением .properties
    @Key("baseUrl") //Если у вас есть составное имя, как у нас, url.base вы можете использовать @Config.Key аннотацию, чтобы связать свойство в general.properties файле с классом.
   @DefaultValue("https://novo-estate.ru/")
    String baseUrl();

    @Key("browser")
   @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
   @DefaultValue("110")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1366x768")
    String browserSize();

    @Key("remote")
    String  remote();}



