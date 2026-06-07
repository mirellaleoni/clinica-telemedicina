import styled from "styled-components";

export const PageGrid = styled.div`
    display: grid;
    gap: 18px;
`;

export const SplitGrid = styled.div`
    display: grid;
    grid-template-columns: minmax(280px, 380px) minmax(0, 1fr);
    gap: 18px;
    align-items: start;

    @media (max-width: 980px) {
        grid-template-columns: 1fr;
    }
`;

export const Panel = styled.section`
    background: #ffffff;
    border: 1px solid #dce5f0;
    border-radius: 8px;
    padding: 18px;
    box-shadow: 0 16px 40px rgba(22, 41, 70, 0.07);
`;

export const PanelHeader = styled.div`
    display: flex;
    justify-content: space-between;
    gap: 12px;
    margin-bottom: 16px;

    h2 {
        margin: 0;
        color: #16243c;
        font-size: 1.05rem;
    }

    p {
        margin: 4px 0 0;
        color: #6d7f96;
        font-size: 0.92rem;
    }
`;

export const FormGrid = styled.form`
    display: grid;
    gap: 12px;
`;

export const Field = styled.label`
    display: grid;
    gap: 6px;
    color: #25344d;
    font-weight: 800;
    font-size: 0.92rem;

    input,
    select,
    textarea {
        width: 100%;
        border: 1px solid #cbd6e5;
        border-radius: 8px;
        background: #ffffff;
        color: #172033;
        padding: 11px 12px;
        font: inherit;
        font-weight: 600;
        outline: none;
    }

    input[type="checkbox"] {
        width: 18px;
        height: 18px;
        accent-color: #1e6ed8;
        box-shadow: none;
    }

    textarea {
        min-height: 120px;
        resize: vertical;
    }

    input:focus,
    select:focus,
    textarea:focus {
        border-color: #2372d9;
        box-shadow: 0 0 0 3px rgba(35, 114, 217, 0.15);
    }
`;

export const Row = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 10px;

    > label {
        flex: 1 1 150px;
    }
`;

export const PrimaryButton = styled.button`
    min-height: 40px;
    border: 0;
    border-radius: 8px;
    background: #1e6ed8;
    color: #ffffff;
    padding: 0 16px;
    font-weight: 800;
    cursor: pointer;

    &:hover {
        background: #195bb1;
    }

    &:disabled {
        cursor: not-allowed;
        background: #95a8c3;
    }
`;

export const SecondaryButton = styled.button`
    min-height: 40px;
    border: 1px solid #cbd6e5;
    border-radius: 8px;
    background: #ffffff;
    color: #1d2c44;
    padding: 0 14px;
    font-weight: 800;
    cursor: pointer;

    &:hover {
        border-color: #1e6ed8;
        color: #1e6ed8;
    }

    &:disabled {
        cursor: not-allowed;
        border-color: #d9e1ec;
        color: #9aabc0;
    }
`;

export const DangerButton = styled(SecondaryButton)`
    &:hover {
        border-color: #d43f3f;
        color: #b72828;
    }
`;

export const TableWrap = styled.div`
    overflow-x: auto;
`;

export const DataTable = styled.table`
    width: 100%;
    min-width: 720px;
    border-collapse: collapse;

    th,
    td {
        padding: 12px 10px;
        border-bottom: 1px solid #e4ebf3;
        text-align: left;
        vertical-align: top;
    }

    th {
        color: #506278;
        font-size: 0.8rem;
        text-transform: uppercase;
        letter-spacing: 0;
    }

    td {
        color: #25344d;
        font-weight: 600;
    }
`;

export const Badge = styled.span`
    display: inline-flex;
    align-items: center;
    min-height: 26px;
    border-radius: 8px;
    background: ${({ $tone }) => ($tone === "success" ? "#dff6ef" : $tone === "danger" ? "#ffe4e4" : "#e7eef8")};
    color: ${({ $tone }) => ($tone === "success" ? "#11614d" : $tone === "danger" ? "#9e2424" : "#234b7c")};
    padding: 3px 9px;
    font-size: 0.82rem;
    font-weight: 800;
`;

export const Message = styled.p`
    margin: 0;
    border-radius: 8px;
    padding: 11px 12px;
    background: ${({ $type }) => ($type === "error" ? "#ffe9e9" : "#e7f7ef")};
    color: ${({ $type }) => ($type === "error" ? "#9c2626" : "#176142")};
    font-weight: 800;
`;

export const EmptyState = styled.div`
    display: grid;
    place-items: center;
    min-height: 180px;
    border: 1px dashed #cbd6e5;
    border-radius: 8px;
    color: #6d7f96;
    text-align: center;
    padding: 20px;
    font-weight: 700;
`;

export const StatGrid = styled.div`
    display: grid;
    grid-template-columns: repeat(4, minmax(160px, 1fr));
    gap: 14px;

    @media (max-width: 980px) {
        grid-template-columns: repeat(2, minmax(0, 1fr));
    }

    @media (max-width: 560px) {
        grid-template-columns: 1fr;
    }
`;

export const StatCard = styled.div`
    background: #ffffff;
    border: 1px solid #dce5f0;
    border-radius: 8px;
    padding: 16px;

    span {
        color: #6d7f96;
        font-weight: 800;
        font-size: 0.85rem;
    }

    strong {
        display: block;
        margin-top: 8px;
        color: #14233c;
        font-size: 2rem;
    }
`;
