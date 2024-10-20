import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { getAuth, provideAuth } from '@angular/fire/auth';

import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { tokenInterceptor } from './core/interceptor/token.interceptor';

const firebaseConfig = {
  apiKey: "AIzaSyAjc5s_Tg2gT-CkIz2YJYM_YImGNMBvgDQ",
  authDomain: "urban-chic-auth.firebaseapp.com",
  projectId: "urban-chic-auth",
  storageBucket: "urban-chic-auth.appspot.com",
  messagingSenderId: "97437208186",
  appId: "1:97437208186:web:135cb1ff78b8b3eed9b5c3"
};

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes),
    provideHttpClient(withInterceptors([tokenInterceptor])), provideAnimationsAsync(),
    provideFirebaseApp(() => initializeApp(firebaseConfig)),
    provideAuth(() => getAuth())
  ]
};


