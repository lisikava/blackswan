import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { Subject } from 'rxjs';
import { MatChipsModule, MatChipInputEvent } from '@angular/material/chips';
import {MatIconModule} from '@angular/material/icon';
import { COMMA, ENTER } from '@angular/cdk/keycodes';

import { Artwork as ArtworkService } from '../../../services/artwork';
import { ArtworkDto } from '../../../services/artwork';
import { Shop } from '../../shop.model';
import { Shop as ShopService } from '../../../services/shop';
import { AiTagging } from '../../../services/ai-tagging';

@Component({
  selector: 'app-upload-artwork',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule, MatFormFieldModule, MatCardModule, MatButtonModule, MatChipsModule, MatSelectModule],
  templateUrl: './upload-artwork.html',
  styleUrl: './upload-artwork.css',
})
export class UploadArtwork {
  loading = false;
  artworkForm: FormGroup;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;

  // tags = signal<string[]>([]);
  selectedFile?: File;
  shop?: Shop;

  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private artworkService: ArtworkService,
    private aiService: AiTagging,
    private router: Router
  ) {
    this.artworkForm = this.fb.group({
      title: new FormControl('', {nonNullable: true, validators: [Validators.required, Validators.minLength(3)]}),
      description: new FormControl('', {nonNullable: false, validators: Validators.minLength(3) }),
      price: new FormControl(0, { nonNullable: true, validators: Validators.min(0) }),
      tags: new FormControl<string[]>([], { nonNullable: true }),
      status: 'AVAILABLE',
      type: ['', [Validators.required]],
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
  }

  get tagsControl() {
    return this.artworkForm.controls['tags'];
  }

  onSubmit() {
    if (!this.shop || !this.selectedFile) return;
    if (this.artworkForm.valid) {
      this.artworkService.uploadArtwork(this.artworkForm.value as any, this.selectedFile)
      .subscribe(() => this.router.navigate(['/dashboard']));
    }
  }

addTag(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      const currentTags = this.tagsControl.value;
      this.tagsControl.setValue([...currentTags, value]);
      this.tagsControl.updateValueAndValidity();
    }
    event.chipInput!.clear();
  }

removeTag(tag: string): void {
  const currentTags = this.tagsControl.value;
  const index = currentTags.indexOf(tag);
  if (index >= 0) {
    const newTags = [...currentTags];
    newTags.splice(index, 1);
    this.tagsControl.setValue(newTags);
    this.tagsControl.updateValueAndValidity();
  }
}

suggestTags() {
  if (!this.selectedFile) return;
  this.loading = true;

  this.aiService.suggestTags(this.selectedFile).subscribe({
    next: (tags) => {
      const currentTags = this.tagsControl.value || [];
      const mergedTags = Array.from(new Set([...currentTags, ...tags]));
      this.tagsControl.setValue(mergedTags);
      this.loading = false;
    },
    error: (err) => console.error('AI Analysis failed', err)
  });
}


}
