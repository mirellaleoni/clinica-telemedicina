import styled from "styled-components";

export const AppShell = styled.div`
    display: grid;
    grid-template-columns: 260px minmax(0, 1fr);
    min-height: 100vh;
    color: #172033;

    @media (max-width: 860px) {
        grid-template-columns: 1fr;
    }
`;

export const Sidebar = styled.aside`
    background: #10243f;
    color: #ffffff;
    padding: 24px 18px;
    border-right: 1px solid rgba(255, 255, 255, 0.08);

    @media (max-width: 860px) {
        position: sticky;
        top: 0;
        z-index: 10;
        padding: 14px;
    }
`;

export const Brand = styled.div`
    display: flex;
    align-items: center;
    gap: 12px;
    margin-bottom: 28px;

    span {
        display: grid;
        place-items: center;
        width: 42px;
        height: 42px;
        border-radius: 8px;
        background: #36b4a7;
        color: #082136;
        font-weight: 800;
    }

    strong {
        line-height: 1.2;
    }

    @media (max-width: 860px) {
        margin-bottom: 14px;
    }
`;

export const Nav = styled.nav`
    display: grid;
    gap: 6px;

    a {
        color: #cbd8ea;
        text-decoration: none;
        padding: 11px 12px;
        border-radius: 8px;
        font-weight: 700;
        transition: 0.18s ease;
    }

    a:hover,
    a.active {
        background: #ffffff;
        color: #10243f;
    }

    @media (max-width: 860px) {
        display: flex;
        overflow-x: auto;

        a {
            white-space: nowrap;
        }
    }
`;

export const Content = styled.main`
    min-width: 0;
    padding: 28px;

    @media (max-width: 680px) {
        padding: 18px;
    }
`;

export const Header = styled.header`
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    gap: 18px;
    margin-bottom: 24px;

    @media (max-width: 680px) {
        flex-direction: column;
    }
`;

export const HeaderText = styled.div`
    h1 {
        margin: 0 0 5px;
        font-size: clamp(1.7rem, 2vw, 2.35rem);
        color: #12213a;
    }

    p {
        margin: 0;
        color: #60718a;
    }
`;

export const Actions = styled.div`
    display: flex;
    gap: 10px;
`;

export const SignOutButton = styled.button`
    height: 40px;
    border: 1px solid #cbd6e5;
    border-radius: 8px;
    background: #ffffff;
    color: #1d2c44;
    padding: 0 16px;
    font-weight: 800;
    cursor: pointer;

    &:hover {
        border-color: #e14d4d;
        color: #b72828;
    }
`;
