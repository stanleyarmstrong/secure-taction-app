import axios from 'axios';
import {authToken} from './authToken';

export const getBudget = (userId, budgetId) => {
  return axios
    .get(
      'http://localhost:10180/budget/' +
        budgetId +
        '/81718C54-4B2C-4131-AD0F-D8726B0A9F4B',
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
      return error;
    });
};
