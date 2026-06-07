import styled from "styled-components";

export const LoginPage = styled.main`
    display: grid;
    grid-template-columns: minmax(0, 1fr) 420px;
    align-items: center;
    gap: 36px;
    width: min(1100px, calc(100% - 32px));
    min-height: 100vh;
    margin: 0 auto;

    @media (max-width: 860px) {
        grid-template-columns: 1fr;
        align-content: center;
        padding: 28px 0;
    }
`;

export const LoginPanel = styled.section`
    color: #11233c;

    span {
        display: grid;
        place-items: center;
        width: 58px;
        height: 58px;
        border-radius: 8px;
        background: #36b4a7;
        color: #082136;
        font-weight: 900;
        margin-bottom: 22px;
    }

    h1 {
        max-width: 640px;
        margin: 0;
        font-size: clamp(2.2rem, 4vw, 4.5rem);
        line-height: 1;
    }

    p {
        max-width: 540px;
        margin: 18px 0 0;
        color: #596b82;
        font-size: 1.08rem;
        line-height: 1.6;
    }
`;

export const LoginCard = styled.section`
    background: #ffffff;
    border: 1px solid #dce5f0;
    border-radius: 8px;
    padding: 28px;
    box-shadow: 0 18px 55px rgba(22, 41, 70, 0.11);

    h2 {
        margin: 0 0 18px;
        color: #14233c;
        font-size: 1.45rem;
    }

    form {
        display: grid;
        gap: 14px;
    }

    div {
        display: grid;
        gap: 7px;
    }

    input {
        width: 100%;
        border: 1px solid #cbd6e5;
        border-radius: 8px;
        color: #172033;
        padding: 12px;
        font: inherit;
        outline: none;
    }

    input:focus {
        border-color: #2372d9;
        box-shadow: 0 0 0 3px rgba(35, 114, 217, 0.15);
    }

    small,
    strong {
        color: #aa2d2d;
        font-size: 0.9rem;
    }

    button {
        width: 100%;
        height: 44px;
        border-radius: 8px;
    }

    a {
        color: #1e6ed8;
        font-weight: 800;
        text-align: center;
        text-decoration: none;
    }
`;
