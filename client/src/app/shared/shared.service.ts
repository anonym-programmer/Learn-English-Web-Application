import {Injectable} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor(private toastr: ToastrService) {
  }

  public showSuccessToastr(msg: string) {
    this.toastr.success(msg, 'Success');
  }

  public showFailureToastr(msg: string) {
    this.toastr.error(msg, 'Failure');
  }

  public showInfoAlert(title: string, msg: string) {
    swal(
      `${title}`,
      `${msg}`,
      'info'
    );
  }

  public showSuccessAlert(title: string, msg: string) {
    swal(
      `${title}`,
      `${msg}`,
      'success'
    );
  }

  public showErrorAlert(title: string, msg: string) {
    swal(
      `${title}`,
      `${msg}`,
      'error'
    );
  }
}
