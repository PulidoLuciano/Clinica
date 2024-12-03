const login  = async(user)=>{
    
    const {email,password} = user;
    const response = await fetch(`${API_BASE_URL}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
    });
    const userData = await response.json();
    if (!response.ok) throw new Error(`${userData.message}`) 
        
    sessionStorage.setItem("jwt", userData.accessToken);
    return userData;
}


export const authService = {login}