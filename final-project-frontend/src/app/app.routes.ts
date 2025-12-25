import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { CreateShop } from './shop/create-shop/create-shop';
import { Dashboard  } from './shop/dashboard/dashboard';
import { EditShop } from './shop/edit-shop/edit-shop';
import { authGuard } from './auth/guard/auth-guard';
import { UploadArtwork } from './shop/artwork/upload-artwork/upload-artwork';
import { PublicShop } from './shop/public-shop/public-shop';
import { Homepage } from './homepage/homepage';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'register', component: Register },
    { path: 'shop/create', component: CreateShop },
    { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
    { path: 'shop/edit', component: EditShop, canActivate: [authGuard] },
    { path: 'shop/upload', component: UploadArtwork, canActivate: [authGuard] },
    { path: 'shops/:slug', component: PublicShop },
    { path: '', component: Homepage }
    // { path: '', redirectTo: 'login', pathMatch: 'full' }
];
