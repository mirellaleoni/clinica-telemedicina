import { createGlobalStyle } from "styled-components";

export const GlobalStyled = createGlobalStyle`
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    html {
        min-height: 100%;
    }

    body {
        max-width: 100vw;
        min-height: 100vh;
        background: #f4f7fb;
        font-family: Inter, ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
    }

    #root {
        min-height: 100vh;
    }

    a {
        color: #1e6ed8;
    }
`;
