import axios from 'axios';
import {authToken} from './authToken';

export const getAccounts = () => {
  //needs to have backend query made
  return axios
    .get('http://localhost:10180/account/demoUser', {
      Authorization: authToken,
    })
    .then((success) => {
      return success.data;
    })
    .catch((error) => {
      console.error('Request failed: ' + error);
    });
};