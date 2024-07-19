window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    url: "http://localhost:8080/HRMS/api/swagger.json",
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
};

function addAuthorizeButton() {
        const authBtn = document.createElement('button');
        authBtn.innerText = 'Authorize';
        authBtn.onclick = () => {
            const token = prompt('Enter JWT Token:');
            if (token) {
                localStorage.setItem('jwtToken', token);
                alert('Token stored successfully!');
            }
        };
        document.getElementById('swagger-ui').prepend(authBtn);
    }

    document.addEventListener('DOMContentLoaded', addAuthorizeButton);
