window.onload = function() {
    //<editor-fold desc="Changeable Configuration Block">

    // Initialize Swagger UI
    window.ui = SwaggerUIBundle({
        url: "http://localhost:8080/HRMS/api/swagger.json", // or the URL to your OpenAPI 3.0 specification
        dom_id: '#swagger-ui',
        docExpansion: 'none',
        deepLinking: true,
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout",
        requestInterceptor: (req) => {
            const token = localStorage.getItem('jwtToken');
            if (token) {
                req.headers.Authorization = `Bearer ${token}`;
            }
            return req;
        }
    });

    //</editor-fold>

    // Function to add the "Authorize" button
    function addAuthorizeButton() {
        const swaggerHeader = document.querySelector('.swagger-ui .topbar');
        if (swaggerHeader) {
            const authBtn = document.createElement('button');
            authBtn.id = 'authorize-btn';
            authBtn.innerText = 'Authorize';
            authBtn.onclick = () => {
                const token = prompt('Enter JWT Token:');
                if (token) {
                    localStorage.setItem('jwtToken', token);
                    alert('Token stored successfully!');
                }
            };
            swaggerHeader.appendChild(authBtn);
        } else {
            console.error('Swagger header not found. Make sure Swagger UI is fully loaded.');
        }
    }

    // Add the "Authorize" button when Swagger UI is fully loaded
    setTimeout(addAuthorizeButton, 10); // Increased timeout to 3000ms (3 seconds)
};
