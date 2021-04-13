import React from 'react';
import {StyleSheet} from 'react-native';
import {Card, Button, Divider} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';
import {View} from 'react-native';
import CardRow from './cardrow';

const InnerScreen = (props) => {
  const navigation = useNavigation();
  return (
    <View style={styles.card}>
      <Card style={styles.inner}>
        <Card.Title
          title={props.title}
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
          <Button
            style={styles.newCard}
            onPress={() => {
              navigation.push('addcard');
            }}>
            Add New Card
          </Button>
        </Card.Content>
      </Card>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    marginTop: 50,
    marginLeft: 25,
    marginRight: 25,
  },
  inner: {
    shadowColor: '#000000',
    shadowOpacity: 20,
    borderRadius: 12,
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
    marginTop: 10,
    textAlign: 'center',
  },
});

export default InnerScreen;
