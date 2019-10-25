package com.pcdeveloper.darkmovies.di.builder;


import com.pcdeveloper.darkmovies.ui.splash.SplashActivity;

import dagger.Module;

/*Conceito Novo
ActivityBuilder : criamos este módulo. Este é um módulo dado ao punhal.
Mapeamos todas as nossas atividades aqui.
E Dagger conhece nossas atividades em tempo de compilação.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidIxnjector
    abstract SplashActivity bindSplashActivity();
}
