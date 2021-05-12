import { CitiesService } from '../../services/cities.service';
import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { City } from 'src/app/models/city';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name'];
  dataSource = new MatTableDataSource<City>();

  totalElements: number = 0;

  pageEvent!: PageEvent;

  constructor(private citiesService: CitiesService) { }

  ngOnInit(): void {
    this.getCities(0, 5);

  }

  public getCities(page: number, size: number) {
    let response = this.citiesService.getCities(page, size);
    response.subscribe( response => {
      this.dataSource.data = response.content
      this.totalElements = response.totalElements;
    });
  }

  public onPageChange(event: PageEvent) {
    this.getCities(event.pageIndex, event.pageSize);
  }

  public applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
