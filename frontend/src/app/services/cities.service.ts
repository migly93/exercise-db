import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CitiesResponse } from '../models/cities-response';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  private citiesApi: string = 'http://localhost:8080/api/cities/queryByPage'

  constructor(private http:HttpClient) { }

  public getCities(page: number, size: number) {
    let params = new HttpParams();
    params = params.append('page', String(page));
    params = params.append('size', String(size));

    return this.http.get<CitiesResponse>(this.citiesApi, {params: params});
  }
}
