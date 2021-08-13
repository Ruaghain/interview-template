import React, { useEffect, useState } from 'react';
import { getAllAccounts } from "./Accounts.model";
import AccountList from "./AccountList/AccountList";

function Accounts() {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const result = await getAllAccounts();
            setData(result.data);
        }
        fetchData();
    }, []);

    return (
        <div>
            <AccountList accountData={data}/>
        </div>
    )

}

export default Accounts;
