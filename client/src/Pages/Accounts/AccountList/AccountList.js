import React from "react";
import { DataGrid } from '@material-ui/data-grid';
import * as PropTypes from "prop-types";
import { Button } from "@material-ui/core";

const columns = [
    {
        field: 'id',
        headerName: 'ID',
        width: 90
    },
    {
        field: 'accountNumber',
        headerName: 'Account Number',
        width: 200,
        editable: false
    },
    {
        field: 'accountName',
        headerName: 'Account Name',
        width: 200,
        editable: false
    },
    {
        field: 'accountBalance',
        headerName: 'Account Balance',
        width: 200,
        editable: false
    },
    {
        field: 'edit',
        headerName: 'Edit',
        width: 300,
        editable: false,
        renderCell: (params) => (
            <div>
                {(params.value)}
                <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    style={{marginLeft: 16}}
                >
                    Edit
                </Button>
            </div>
        )
    }

];

function AccountList(props) {
    return (
        <div style={{ height: 400, width: '100%' }}>
            <DataGrid
                rows={props.accountData}
                columns={columns}
                pageSize={5}
            />
        </div>
    );
}

AccountList.propTypes = {
    accountData: PropTypes.array
};

export default AccountList
