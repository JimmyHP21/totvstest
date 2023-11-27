import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { SystemService } from './service/system.service';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ModuleService } from '../service/module.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-new-system',
  templateUrl: './new-system.component.html',
  styleUrls: ['./new-system.component.scss']
})
export class NewSystemComponent implements OnInit {
  form: FormGroup
  formModule: FormGroup;
  idSystem: any;

  isLoadingData = false;

  constructor(private fb: FormBuilder,
              private systemService: SystemService,
              private moduleService: ModuleService,
              private route: ActivatedRoute,
              private _location: Location, private toastr: ToastrService) {
  }


  ngOnInit() {

    this.form = this.fb.group({
      id: [],
      name: [null, [Validators.required]],
      cpf: [null, [Validators.required]],
      address: [null, [Validators.required]],
      neighborhood: [null, [Validators.required]],
      numberList: this.fb.array([])
    })

    this.idSystem = this.route.snapshot.paramMap.get('id') || null;

    if (this.idSystem) {
      this.systemService.getSystem(this.idSystem).subscribe((dta: any) => {
        this.form.controls['id'].setValue(dta.id);
        this.form.controls['name'].setValue(dta.name);
        this.form.controls['cpf'].setValue(dta.cpf);
        this.form.controls['address'].setValue(dta.address);
        this.form.controls['neighborhood'].setValue(dta.neighborhood);
        this.addNumbers(dta.numberList);
      })
    }

  }

  addNumbers(array: any) {
    let l = this.form.controls["numberList"] as FormArray;
    array.forEach((element: any) => {
        l.push(this.fb.group({
        id: [element.id],
        number: [element.number],
        client_id: [element.client_id]
      }))
    });

  }

  clearForm () {
    this.form = this.fb.group({
      name: [null, [Validators.required]],
      cpf: [null, [Validators.required]],
      address: [null, [Validators.required]],
      neighborhood: [null, [Validators.required]],
      numberList: this.fb.array([this.createFormGroup()])
    })
  }

  try() {
    console.log(this.form.value);
  }

  save() {
    console.log(this.form.value);
    if (this.numbers.length > 0) {
      if (this.idSystem) {
        this.systemService.updateSystem(this.form.value).subscribe((dta) => {
          this.toastr.success('Client ' + this.form.value.name + ' Atualizado com Sucesso', 'Sucesso', {
            timeOut: 3000,
          })
        } , (erro) => {
            if (erro.error.developersError) {
              erro.error.developersError.forEach((e: any) => {
                this.toastr.error(e.msg, erro.status,  {
                  timeOut: 3000
                })
              })
            } else {
              this.toastr.error(erro.error.message, erro.error.status,  {
                timeOut: 3000
              })
            }
          });
      } else {
        this.systemService.saveSystem(this.form.value).subscribe((data) => {
          this.toastr.success('Client ' + data.name + ' Salvo com Sucesso', 'Sucesso', {
            timeOut: 3000,
          })
        } , (erro) => {
          if (erro.error.developersError) {
            erro.error.developersError.forEach((e: any) => {
              this.toastr.error(e.msg, erro.status,  {
                timeOut: 3000
              })
            })
          } else {
            this.toastr.error(erro.error.message, erro.error.status,  {
              timeOut: 3000
            })
          }

        })
      }
    } else {
      this.toastr.error('Precisa ter um telefone valido', 'Telefone',  {
        timeOut: 3000
      })
    }
  }

  newPhone() {
    this.numbers.push(this.createFormGroup());
  }

  removeNumber(i: any, obj: any) {
    this.numbers.removeAt(i);
  }

  createFormGroup() {
    return this.fb.group({
      number: [null],
      client_id: [this.idSystem || null]
    });
  }

  get numbers(): FormArray {
    return this.form.controls["numberList"] as FormArray;
  }

}
