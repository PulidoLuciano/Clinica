export class ApiError extends Error{
    constructor(data){
        super("La api ha devuelto un error");
        this.data = data;
    }
}