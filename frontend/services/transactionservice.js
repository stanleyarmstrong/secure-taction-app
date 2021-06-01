import axios from 'axios';
import {authToken} from './authToken';

export const getTransactions = (accountId) => {
  return axios
    .post(
      'http://localhost:10180/plaid/transactions',
      {
        publicToken: accountId,
        userId: '5CC6D297-2415-4A36-8E61-79C011C3C9EF',
        userName: 'JohnnyBoy7',
      },
      {
        headers: {
          Authorization: authToken,
        },
      },
    )
    .then((success) => {
      return success.data;
    })
    .catch((error) => {
      console.error(error);
      return [];
    });
};
