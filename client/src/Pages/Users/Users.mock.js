import MockAdapter from "axios-mock-adapter";

const mockedUsers = [
    {
        id: 1,
        userName: "one.person",
        firstName: "One",
        lastName: "Person",
        email: "one.person@workhuman.com",
        password: "p@ssw0rd"
    },
    {
        id: 2,
        userName: "two.person",
        firstName: "Two",
        lastName: "Person",
        email: "two.person@workhuman.com",
        password: "p@ssw0rd"
    }
]

export const setupUsersMock = (client) => {
    const mock = new MockAdapter(client);

    mock.onGet(new RegExp('/api/v1/user')).reply(
        200,
        mockedUsers
    );
    mock.onGet(new RegExp('/api/v1/user/*')).reply(
        200,
        mockedUsers[0]
    )
}
