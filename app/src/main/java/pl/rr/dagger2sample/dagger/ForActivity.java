package pl.rr.dagger2sample.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Rafal on 2015-06-18.
 */
@Scope @Retention(RUNTIME)
public @interface ForActivity {
}
