package de.slevermann.cocktails.model.db;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DbUserInfo {

    @NonNull UUID uuid;

    @NonNull String providerId;

    String nick;
}
