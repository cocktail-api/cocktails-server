package de.slevermann.cocktails.dbmodels;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DbUserInfo {

    UUID uuid;

    String providerId;

    String nick;
}
