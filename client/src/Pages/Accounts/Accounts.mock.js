import MockAdapter from "axios-mock-adapter";

const mockedAccounts = [
    {
        id: 1,
        accountNumber: "123456",
        accountName: "Current",
        accountBalance: 123.45
    },
    {
        id: 2,
        accountNumber: "234567",
        accountName: "Savings",
        accountBalance: 1234.56
    }
]

export const setupAccountsMock = (client) => {
    const mock = new MockAdapter(client);

    mock.onGet(new RegExp('/api/v1/account')).reply(
        200,
        mockedAccounts
    );
    mock.onGet(new RegExp('/api/v1/account/*')).reply(
        200,
        mockedAccounts[0]
    )
}
