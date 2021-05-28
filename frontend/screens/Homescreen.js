import React, {useState, useEffect} from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import {Card, Button, Divider} from 'react-native-paper';
import {PlaidLink} from 'react-native-plaid-link-sdk';
import CardRow from '../components/cardrow';
import {getLinkToken, tokenExchange} from '../services/plaidService';
import {getAccounts} from '../services/accountService';

const HomeScreen = (props) => {
  const [linkToken, setLinkToken] = useState('');
  const [cardRows, setCardRows] = useState([]);
  useEffect(() => {
    getLinkToken().then((token) => {
      setLinkToken(token);
    });
    getAccounts()
      .then((data) => {
        setCardRows(
          data.map((account) => {
            return (
              <CardRow
                key={account.accountId}
                id={account.accountId}
                budgetId={account.budgetId}
                name={account.accountName}
                progress={
                  account.currentBudgetBalance / account.maxBudgetBalance
                }
                alert={account.minimumAlert}
                cancel={account.autoCancel}
                balance={account.accountBalance}
              />
            );
          }),
        );
      })
      .catch((error) => {
        console.error(error);
      });
  }, [cardRows]);
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title="Your Cards"
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          {cardRows}
          <Divider />
          <PlaidLink
            tokenConfig={{
              token: linkToken,
            }}
            onSuccess={(success) => {
              tokenExchange(
                success.publicToken,
                '81718C54-4B2C-4131-AD0F-D8726B0A9F4B',
                'demoUser',
              );
            }}
            onExit={(exit) => {
              console.error(exit.errorMessage);
            }}>
            <Button style={styles.newCard} color="#00A7E1">
              Add New Card
            </Button>
          </PlaidLink>
        </Card.Content>
      </Card>
    </View>
  );
};

const styles = StyleSheet.create({
  shell: {
    backgroundColor: '#E9E9E9',
    height: '100%',
  },
  inner: {
    shadowColor: '#000000',
    shadowOpacity: 20,
    borderRadius: 12,
    marginTop: 50,
    marginLeft: 25,
    marginRight: 25,
  },
  title: {
    color: '#FFFFFF',
    backgroundColor: '#00A7E1',
    fontSize: 24,
    borderRadius: 12,
  },
  titleColor: {
    color: '#FFFFFF',
  },
  newCard: {
    paddingTop: 10,
    textAlign: 'center',
  },
});
export default HomeScreen;
