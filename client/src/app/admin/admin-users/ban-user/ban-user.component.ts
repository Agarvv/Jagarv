import { Component, Input } from '@angular/core';
import { BanService } from '@services/ban/ban.service';
import { Store } from '@ngrx/store';
import { clearMessages, setError } from '@store/admin/admin.actions';

@Component({
    selector: 'app-ban-user',
    templateUrl: './ban-user.component.html',
    styleUrl: './ban-user.component.css'
})
export class BanUserComponent {
    @Input() userId: number | null = null; 
    @Input() isBanned: boolean | false = false; 

    constructor(private banService: BanService, private store: Store) {} 

    banOrUnban(): void {
        this.store.dispatch(clearMessages());
        if (this.userId === null) {
            console.error('User ID is null');
            this.store.dispatch(setError({
                errorMessage: 'User ID is required.'
            }));
            return;
        }

        this.banService.banOrUnbanUser(this.userId)
            .subscribe((data: string) => {
                
                console.log('ban data', data);
                
                if(data.message == 'BANNED') {
                    this.isBanned = true; 
                } else {
                    this.isBanned = false;
                }
                
            }, (error) => {
                console.error(error);
                this.store.dispatch(setError({
                    errorMessage: 'Something went wrong...'
                }));
            });
    }
}