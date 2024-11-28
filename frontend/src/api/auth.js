const API_BASE_URL = "http://localhost:8080/login";

export const authenticateUser = async (email, password) => {
    try {
        const response = await fetch(`${API_BASE_URL}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ email, password }),
        });

        if (response.ok) {
            const data = await response.json();
            sessionStorage.setItem("jwt", data.accessToken);
            return { isAuthenticated: true, error: null };
        } else {
            const errorData = await response.json();
            return { isAuthenticated: false, error: errorData.message };
        }
    } catch (error) {
        return { isAuthenticated: false, error: error.message };
    }
};
