import { Artwork } from "./artwork/artwork.model";

export interface Shop {
    id: number;
    name: string;
    slug: string;
    description: string;
    artworks?: Artwork[];
    aiTags?: boolean;
}