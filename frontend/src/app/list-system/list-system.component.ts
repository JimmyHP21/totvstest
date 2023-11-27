import {
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  ViewEncapsulation
} from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { SystemService } from "../new-system/service/system.service";

@Component({
  selector: 'app-list-system',
  templateUrl: './list-system.component.html',
  styleUrls: ['./list-system.component.scss']
})
export class ListSystemComponent implements OnInit {
  displayedColumns: string[] = ['system', 'codSystem', 'description', 'sigla', 'actions'];
  dataSource: MatTableDataSource<any[]> = new MatTableDataSource<any[]>([]);

  @ViewChild(MatPaginator, { static: true })
  paginator: MatPaginator;

  @ViewChild(MatSort, { static: true })
  sort: MatSort;

  isLoadingData = false;

  constructor(private systemService: SystemService) { }


  ngOnInit(): void {
    this.list();
  }


  list() {
    this.systemService.listSystem().subscribe((data: any) => {
      this.dataSource.data = data;
    })
  }

  delete(id: any) {
    this.systemService.deleteSystem(id).subscribe((dta: any) =>{
      this.list();
    })
  }

}
