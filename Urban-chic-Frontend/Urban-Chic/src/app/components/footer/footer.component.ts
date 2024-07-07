import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent implements OnInit{

  str='"mai madarchod hu"';
 
 

  constructor(private http: HttpClient){}
  ngOnInit(): void {
    console.log("hellow muther fucker")
    // this.getMethod();
    // this.postMethod(this.str);
    // this.register(this.women);
  }

  getMethod(){
    return this.http.get('http://192.168.1.34:8081/auth/check').subscribe({
      next: response => {console.log(response);},
      error: error=>{console.log(error);}
    })
  }

  postMethod(str: string){
    const body={str};
    this.http.post('http://192.168.1.34:8087/send', body).subscribe({

      next: response=>{
        console.log(response)
      },
      error: error=>{
        console.log(error)
      }

    })
  }
  register(women: any){
    const body={women};
    this.http.post('http://192.168.1.34:8082/users/create', body).subscribe({

      next: response=>{
        console.log(response)
      }

    })
  }

}
