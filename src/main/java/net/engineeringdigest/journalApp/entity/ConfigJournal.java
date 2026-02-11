package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "config_journal_app")
//@Getter
//@Setter
@Data
@NoArgsConstructor
public class ConfigJournal
{

    private String key;
    private String value;




}
