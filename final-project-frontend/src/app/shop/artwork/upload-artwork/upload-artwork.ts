import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators, NgModel } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { Subject } from 'rxjs';

import { Artwork as ArtworkService } from '../../../services/artwork';
import { ArtworkDto } from '../../../services/artwork';
import { Shop } from '../../shop.model';
import { Shop as ShopService } from '../../../services/shop';

@Component({
  selector: 'app-upload-artwork',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatCardModule, MatButtonModule],
  templateUrl: './upload-artwork.html',
  styleUrl: './upload-artwork.css',
})
export class UploadArtwork {
  // slug: string;
  artworkForm: FormGroup;
  // artwork: ArtworkDto = {
  //   title: '',
  //   description: '',
  //   price: 0,
  //   tags: [],
  //   status: 'AVAILABLE'
  // };

  tags = '';
  selectedFile?: File;
  shop?: Shop;
  // shop$: Subject<Shop> = new Subject<Shop>();

  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private artworkService: ArtworkService,
    private router: Router
  ) {
    this.artworkForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.minLength(3)]],
      price: [0, [Validators.required, Validators.min(0)]],
      tags: [''],
      status: 'AVAILABLE'
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  ngOnInit() {
    this.shopService.getMyShop().subscribe({
      next: shop => this.shop = shop,
      error: () => this.router.navigate(['/dashboard'])
    });
    // this.slug = this.route.snapshot.paramMap.get('slug')!;
  }

  onSubmit() {
    if (!this.shop || !this.selectedFile) return;
    if (this.artworkForm.valid) {
      this.artworkForm.value['tags'] = this.artworkForm.value['tags'].split(',').map((t: any) => t.trim());
      this.artworkService.uploadArtwork(this.artworkForm.value as any, this.selectedFile)
      .subscribe(() => this.router.navigate(['/dashboard']));
    }
    // this.artwork.tags = this.tags.split(',').map(t => t.trim());
    
  }


}
