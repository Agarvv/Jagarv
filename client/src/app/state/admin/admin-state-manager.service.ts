import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
// manages all the error, loading and success states of all the admin page.
export class AdminStateManagerService {
  private isLoadingSubject = new BehaviorSubject<boolean>(false);
  
  private successMessageSubject = new BehaviorSubject<string | null>(null);
  
  private errorMessageSubject = new BehaviorSubject<string | null>(null);

  isLoading$ = this.isLoadingSubject.asObservable();
  
  successMessage$ = this.successMessageSubject.asObservable();
  
  errorMessage$ = this.errorMessageSubject.asObservable();

  setLoading(loading: boolean) {
    this.isLoadingSubject.next(loading);
    console.log("dlm", loading)
  }

  setSuccess(message: string) {
    this.successMessageSubject.next(message);
  }

  setError(message: string) {
    this.errorMessageSubject.next(message);
  }

  clearMessages() {
    this.successMessageSubject.next(null);
    this.errorMessageSubject.next(null);
  }
}

