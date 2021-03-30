import React from 'react';
import {StyleSheet} from 'react-native';
import {Card} from 'react-native-paper';
import {Text} from 'react-native';

const InnerScreen = (props) => {
  return (
    <Card styles={styles.card}>
      <Card.Title
        title={props.title}
        style={styles.title}
        titleStyle={styles.titleColor}
      />
    </Card>
  );
};

const styles = StyleSheet.create({
  title: {
    color: '#FFFFFF',
    backgroundColor: '#00A7E1',
    fontSize: 24,
  },
  titleColor: {
    color: '#FFFFFF',
  },
});

export default InnerScreen;
