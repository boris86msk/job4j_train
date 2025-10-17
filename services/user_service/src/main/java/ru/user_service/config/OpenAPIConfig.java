package ru.user_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() throws IOException, XmlPullParserException {
        Server myServer = new Server();
        myServer.setDescription("job4j_train user_service");

        Contact contact = new Contact();
        contact.setEmail("boris86msk@yandex.com");
        contact.setName("Boris_Pokidov");
        contact.setUrl("https://t.me/BorisPokidov");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API тестового задания \"Микросервисы\" job4j.ru")
                .version(getProjectVersion())
                .contact(contact)
                .description("This API exposes endpoints to manage project.").termsOfService("https://mydomen.com")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(myServer));
    }

    private String getProjectVersion() throws XmlPullParserException, IOException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        return String.format("v.%s", model.getVersion());
    }
}
