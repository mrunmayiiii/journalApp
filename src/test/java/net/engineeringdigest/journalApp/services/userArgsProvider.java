package net.engineeringdigest.journalApp.services;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.MethodCall;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class userArgsProvider implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
           Arguments.of(User.builder().username("sham").password("sham").roles("USER").build()),
           Arguments.of(User.builder().username("mrunmai").password("").roles("USER").build()),
            Arguments.of(User.builder().username("madhura").password("madhura").roles("USER").build())

        ) ;
    }
}
