import axios from 'axios';
import { setupAccountsMock } from "./Accounts.mock";

const BASE_URL = 'http://localhost:8080/api/v1/account';

const useMocks = process.env.REACT_APP_USE_MOCKS;

const client = axios.create({
        baseURL: BASE_URL
    }
);

if (useMocks) {
    setupAccountsMock(client);
}

export const getAllAccounts = () => {
    return client.get(BASE_URL);
}

export const getAccount = (id) => {
    return client.get(`/${id}`)
}
