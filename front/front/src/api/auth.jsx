import React, { useState } from "react";

const API_BASE_URL = "http://localhost:8080/login";

function useAuthenticateUser() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [error, setError] = useState(null);

    const authenticateUser = async (email, password) => {
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
                setIsAuthenticated(true);
                setError(null);
                return true;
            } else {
                setIsAuthenticated(false);
                setError("Invalid email or password.");
                return false;
            }
        } catch (err) {
            console.error("Error during authentication:", err);
            setIsAuthenticated(false);
            setError("An unexpected error occurred.");
            return false;
        }
    };

    return { isAuthenticated, authenticateUser, error };
}

export default useAuthenticateUser;
