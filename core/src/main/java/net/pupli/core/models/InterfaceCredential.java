package net.pupli.core.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * @since 2022-05-27
 */
@Document(collection = "interface_credentials")
public class InterfaceCredential {

    @Id
    private String id;
    private String credential;
    private DateTime validUntil;
    private boolean isDisabled;


    public InterfaceCredential(String credential, DateTime validUntil, boolean isDisabled) {
        this.credential = credential;
        this.validUntil = validUntil;
        this.isDisabled = isDisabled;
    }

    public InterfaceCredential() {
        this.credential = UUID.randomUUID().toString();
        this.validUntil = DateTime.now().plusYears(1);
        this.isDisabled = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public DateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(DateTime validUntil) {
        this.validUntil = validUntil;
    }

    public boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

}
