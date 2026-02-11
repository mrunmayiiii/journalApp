package net.engineeringdigest.journalApp.entity;

import lombok.*;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Document(collection = "journal_entries")
//@Getter
//@Setter
@Data
@NoArgsConstructor
public class JournalEntry
{

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private Sentiment sentiment;
    private LocalDateTime date;




}
