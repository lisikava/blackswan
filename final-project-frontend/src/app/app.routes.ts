import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { CreateShop } from './shop/create-shop/create-shop';
import { Dashboard  } from './shop/dashboard/dashboard';
import { EditShop } from './shop/edit-shop/edit-shop';
import { authGuard } from './auth/guard/auth-guard';
import { customerGuard } from './auth/guard/customer-guard';
import { UploadArtwork } from './shop/artwork/upload-artwork/upload-artwork';
import { PublicShop } from './shop/public-shop/public-shop';
import { Homepage } from './homepage/homepage';
import { Order } from './order/order';
import { PublicArtwork } from './shop/artwork/public-artwork/public-artwork';
import { OrderSuccess } from './order/order-success/order-success';
import { CustomerOrders } from './order/customer-orders/customer-orders';
import { ArtistOrders } from './order/artist-orders/artist-orders';
import { Search } from './search/search';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'register', component: Register },
    { path: 'shop/create', component: CreateShop },
    { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
    { path: 'shop/edit', component: EditShop, canActivate: [authGuard] },
    { path: 'shop/upload', component: UploadArtwork, canActivate: [authGuard] },
    { path: 'shop/orders', component: ArtistOrders, canActivate: [authGuard] },
    { path: 'shops/:slug', component: PublicShop },
    { path: 'orders/:artworkId', component: Order, canActivate: [customerGuard] },
    { path: 'orders/success/:orderId', component: OrderSuccess, canActivate: [customerGuard] },
    { path: 'my-orders', component: CustomerOrders, canActivate: [customerGuard] },
    { path: '', component: Homepage },
    { path: 'shops/:slug/:artworkId', component: PublicArtwork },
    { path: 'search', component: Search }
    // { path: '', redirectTo: 'login', pathMatch: 'full' }
];
