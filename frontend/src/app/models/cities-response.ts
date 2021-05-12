import { City } from './city';

export interface CitiesResponse {
  content: City[];
  totalPages: number;
  totalElements: number;
}
