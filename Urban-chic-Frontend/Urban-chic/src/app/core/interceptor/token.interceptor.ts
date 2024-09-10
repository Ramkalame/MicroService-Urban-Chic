import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LocalStorageService } from '../services/local-storage.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  const localStorage = inject(LocalStorageService);
  const token = localStorage.getToken();
  if(token){
    const cloned = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`) 
    })
    return next(cloned);
  }
  return next(req);
};
