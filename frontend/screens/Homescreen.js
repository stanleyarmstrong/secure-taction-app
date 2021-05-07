import React, {useState, useEffect} from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import {Card, Button, Divider} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';
import {PlaidLink} from 'react-native-plaid-link-sdk';
import CardRow from '../components/cardrow';
import {getLinkToken, tokenExchange} from '../services/plaidService';

const HomeScreen = (props) => {
  const [linkToken, setLinkToken] = useState('');
  useEffect(() => {
    getLinkToken().then((token) => {
      setLinkToken(token);
    });
  }, []);
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title="Your Cards"
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          <CardRow
            bank={
              'https://www.pikpng.com/pngl/m/257-2578954_chase-bank-chase-bank-chase-bank-clipart.png'
            }
            name={'Chase Ending in ...4415'}
            progress={0.5}
            alert={20.5}
            cancel={300}
            balance={3000}
          />
          <CardRow
            bank={
              'https://www.pikpng.com/pngl/m/257-2578954_chase-bank-chase-bank-chase-bank-clipart.png'
            }
            name={'Chase Ending in ...4415'}
            progress={0.5}
            alert={20.5}
            cancel={300}
            balance={3000}
          />
          <CardRow
            bank={
              'https://www.pikpng.com/pngl/m/257-2578954_chase-bank-chase-bank-chase-bank-clipart.png'
            }
            name={'Chase Ending in ...4415'}
            progress={0.5}
            alert={20.5}
            cancel={300}
            balance={3000}
          />
          <Divider />
          <PlaidLink
            tokenConfig={{
              token: linkToken,
            }}
            onSuccess={(success) => {
              console.log(success.publicToken);
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
